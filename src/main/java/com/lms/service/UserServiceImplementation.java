package com.lms.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.controller.request.BorrowBookRequest;
import com.lms.controller.request.ForgetPasswordRequest;
import com.lms.controller.request.ReturnBookRequest;
import com.lms.controller.request.UserLoginRequest;
import com.lms.controller.request.UserRegisterationRequest;
import com.lms.controller.request.UserUpdateProfileRequest;
import com.lms.controller.response.GlobalResponse;
import com.lms.controller.response.UsersGlobalResponse;
import com.lms.logs.interfaces.LogInterface;
import com.lms.repository.BooksRepository;
import com.lms.repository.UserRepository;
import com.lms.repository.entity.Books;
import com.lms.repository.entity.User;
import com.lms.service.interfaces.IUserService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class UserServiceImplementation implements IUserService {

	@Autowired 
	UserRepository userRepository;
	
	@Autowired
	LogInterface logInterface;
	
	@Autowired
	UserRepository repository;
	
	
	@Autowired
	BooksRepository booksRepository;
	
// USER LOGIN SERVICE
	@Override
	public UsersGlobalResponse userLogin(UserLoginRequest userLoginRequest) {
		User userEntity = userRepository.findByEmailAndPassword(userLoginRequest.getEmail(),userLoginRequest.getPassword());
		if(userEntity==null){
			log.info(logInterface.logInfoForUserAndAdmin(" User  tried to login with invalid crediantials"));
			return new UsersGlobalResponse (" invalid crediantials ") ;
		}
		else {
			log.info(logInterface.logInfoForUserAndAdmin(" "+userEntity.getFullName()+" has logged in successfully "));
		return new UsersGlobalResponse (" "+userEntity.getFullName()+" logged in successfully");
		}	
	}
// USER REGISTERATION SERVICE
	@Override
	public UsersGlobalResponse saveUser(UserRegisterationRequest userRegistrationRequest) {
		Pattern pattern = Pattern.compile("^[a-z0-9]+@[a-z]+\\.[a-zA-Z]{2,}$");
        Matcher matcher = pattern.matcher(userRegistrationRequest.getEmail());
		if(!matcher.matches()) {
			log.warn(logInterface.logInfoForUserAndAdmin("invalid email format or could be empty field"));
			return new UsersGlobalResponse("invalid email format or could be a empty field  ");
		}
		User userEntity = repository.findByEmailAndPhoneNo(userRegistrationRequest.getEmail(),userRegistrationRequest.getPhoneNo());

        if(userEntity==null) {
        	User emailCheck = repository.findByEmail(userRegistrationRequest.getEmail());
    		User phoneNoCheck = repository.findByPhoneNo(userRegistrationRequest.getPhoneNo());
        	
    		if (emailCheck != null) {
    			log.info(logInterface.logInfoForUserAndAdmin("User with this "+emailCheck+" already exists"));
    			return new UsersGlobalResponse("User with this email already exists.");
    		}
    		if (phoneNoCheck != null) {
    			log.info(logInterface.logInfoForUserAndAdmin("User with this "+phoneNoCheck+" already exists"));
    			return new UsersGlobalResponse("User with this phone number already exists.");
    		}
    		
        	User user2 = User.builder().fullName(userRegistrationRequest.getFullName())
    				.email(userRegistrationRequest.getEmail()).phoneNo(userRegistrationRequest.getPhoneNo())
    				.password(userRegistrationRequest.getPassword()).build();
    		repository.save(user2);
    		log.info(logInterface.logInfoForUserAndAdmin("a new user "+userRegistrationRequest.getFullName() +" has registered "));
    		return new UsersGlobalResponse("user registered successfully");
      }		

//		EMPTY FIELDS CHECK 
//		if((userRegistrationRequest.getFullName()==null)||(userRegistrationRequest.getEmail()==null)||(userRegistrationRequest.getPassword()==null)) {
//			logger.info(logInterface.logInfo("registration fields were not filled properly"));
//			return new UsersResponse("while registration some necessary fields were empty");
//		}
        else {		
          	log.info(logInterface.logInfoForUserAndAdmin(" email "+userRegistrationRequest.getEmail()+" and "+userRegistrationRequest.getPhoneNo()+" already exists"));
        			return new UsersGlobalResponse("User with this email or phone number already exist ");       
        }
	}
// USER UPDATE PROFILE
	@Override
	public GlobalResponse updateUserProfile(UserUpdateProfileRequest updateProfileRequest) {
		User userEntity = userRepository.findByEmail(updateProfileRequest.getEmail());
		if (userEntity != null) {
			userEntity.setFullName(updateProfileRequest.getFullName());
			userEntity.setEmail(updateProfileRequest.getEmail());
			userEntity.setPhoneNo(updateProfileRequest.getPhoneNo());
			userEntity.setPassword(updateProfileRequest.getPassword());
			userRepository.save(userEntity);
			log.info(logInterface.logInfoForUserAndAdmin("user's profile has been updated having email "+updateProfileRequest.getEmail()));
	        return new GlobalResponse("users profile has been updated ");	
		}
		else {
			log.warn(logInterface.logWarnForUserAndAdmin("Profile can not be updated,no such user exist with email "+updateProfileRequest.getEmail()));
	        return new GlobalResponse("profile can not be updated because user does not exists");	
		}
	}

// FORGET PASSWORD SERVICE
	@Override
	public UsersGlobalResponse newPasswordSetUp(ForgetPasswordRequest forgetPassword) {
//		LogActivityOfUserAndAdmin activityOfUserAndAdmin = new  LogActivityOfUserAndAdmin();
		User userEntity =userRepository.findByEmail(forgetPassword.getEmail()); 
		if (userEntity == null) {
//    log.error("user entity does't exist for mail {}",forgetPassword.getEmail());
//    activityOfUserAndAdmin.setMessage("user entity does't exist for mail"+forgetPassword.getEmail());
//    LocalDateTime dateTime = LocalDateTime.now();
//    String currentDateTime = dateTime.toString();
//    activityOfUserAndAdmin.setDateTime(currentDateTime);
//    activityOfUserAndAdmin.setLevel("Error");
//    logRepository.save(activityOfUserAndAdmin);
//    log.info("Saved in logRepositoryBooks. Email: {}, currentTime: {}",forgetPassword.getEmail(),activityOfBooks.getDateTime());
		log.warn(logInterface.logWarnForUserAndAdmin("user entity does't exist for mail "+forgetPassword.getEmail()));
		return new  UsersGlobalResponse("user entity does't exist for mail"+forgetPassword.getEmail());
		}
		if (forgetPassword.getNewPassword().equals(forgetPassword.getConfirmPassword())) {
			userEntity.setPassword(forgetPassword.getConfirmPassword());
			userRepository.save(userEntity);
			
			log.info(logInterface.logInfoForUserAndAdmin("password has changed successfully for user having email "+forgetPassword.getEmail()));
			return new  UsersGlobalResponse("password changed successfully ");
		}

		else {
			log.info(logInterface.logInfoForUserAndAdmin("forget password request is not completed"+forgetPassword.getEmail()));
			return new  UsersGlobalResponse("something went wrong while changing the password");
			
		}
	}
// USER BORROW BOOK SERVICE
	@Override
	public UsersGlobalResponse borrowBook(BorrowBookRequest bookRequest) {
User userEntity= userRepository.findByEmail(bookRequest.getEmail());
		
		if (userEntity == null) {
            log.warn(logInterface.logWarnForBooks("User with email " + bookRequest.getEmail() + " not found"));
            return new UsersGlobalResponse("User not found");
        }
		
		List<Books> books = booksRepository.findByBookNameIn(bookRequest.getBorrowBooks());

		if((books == null || books.isEmpty())) {
			log.warn(logInterface.logWarnForBooks(bookRequest.getBorrowBooks()+" are not available"));
		    return new UsersGlobalResponse("these  books are not available or you have entered wrong book name and authors name");
		}
		 List<Books> alreadyBorrowedBooks = userEntity.getBookList().stream()
	                .filter(books::contains)
	                .collect(Collectors.toList());
		if(!alreadyBorrowedBooks.isEmpty()) {
			log.warn(logInterface.logWarnForBooks(bookRequest.getBorrowBooks()+" these books are  already been borrowed by the same user"));
		    return new UsersGlobalResponse("not allowed to borrow same books to same user");
		}
		
		for(Books book :books  ) {
		if(book.getNoOfBooks()==0) {
			log.info(logInterface.logWarnForBooks(book.getBookName()+" is currently not available"));
		    return new UsersGlobalResponse("this book is currently not available ");
		}
		else {
//			to get the book list of the user
		     List<Books> usersBooks = userEntity.getBookList(); 
		     usersBooks.add(book); 
		     
//		     int updateNumberOfBook= book.getNoOfBooks();
//		     updateNumberOfBook--;
		     book.setNoOfBooks(book.getNoOfBooks()-1);
		     
		     userEntity.setBookList(usersBooks);
		     
		     userRepository.save(userEntity);
		     booksRepository.save(book);
			
             log.info(logInterface.logInfoForBooks("Book " + book.getBookName() + " borrowed successfully by user " + bookRequest.getEmail()));

	}
	
	}
		log.info(logInterface.logInfoForBooks(bookRequest.getBorrowBooks()+" are  borrowed by user having email "+bookRequest.getEmail()));
	    return new UsersGlobalResponse("user borrowed the book(s)");
	}
// UUSER RETURN BOOK SERVICE
	@Override
	public UsersGlobalResponse returnBook(ReturnBookRequest returnBookRequest) {
		User userEntity = userRepository.findByEmail(returnBookRequest.getEmail());
		if (userEntity == null) {
			log.warn(logInterface.logWarnForBooks(
					"user having email " + returnBookRequest.getEmail() + " does not exists needs to get registered"));
			return new UsersGlobalResponse("entered invalid email id");
			}
		
			List<Books> booksToReturnList = userEntity.getBookList().stream()
					.filter(book -> returnBookRequest.getBookId().contains(book.getBookId())).collect(Collectors.toList());
			
			
			if (!booksToReturnList.isEmpty()) {
				for(Books book : booksToReturnList){
					Books libraryBook = booksRepository.findByBookId(book.getBookId());
					if(libraryBook==null) {
						log.warn(logInterface.logWarnForBooks("this book is not borrowed from this library"));
				return new UsersGlobalResponse("this book is not borrowed from this library");

					}
				int updateNumberOfBooks = booksRepository.findByBookId(book.getBookId()).getNoOfBooks();
				updateNumberOfBooks++;
				book.setNoOfBooks(updateNumberOfBooks);
				booksRepository.save(book);
				userEntity.getBookList().remove(book);
				userRepository.save(userEntity);

				log.info(logInterface.logInfoForBooks(" book having book id " + returnBookRequest.getBookId()
						+ " is  returned by user having email " + returnBookRequest.getEmail()));
				return new UsersGlobalResponse("user returned the book ");
			}
				}
			else {
	            
	            log.warn(logInterface.logWarnForBooks("User with email " + returnBookRequest.getEmail() + " did not borrow the book with id " + returnBookRequest.getBookId()));
	            return new UsersGlobalResponse("User never borrowed these books with id's " + returnBookRequest.getBookId());
	        }
			
				log.warn(logInterface.logWarnForBooks(" book having book id " + returnBookRequest.getBookId()
						+ " is not returned  by user having email " + returnBookRequest.getEmail()));
				return new UsersGlobalResponse("user didn't returned the book");
	
			
	}

}
