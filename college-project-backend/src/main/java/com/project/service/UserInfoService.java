package com.project.service;
import com.project.config.EmailCfg;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.entity.UserInfo;
import com.project.repository.UserInfoRepository;
import org.springframework.stereotype.Service;
@Service
public class UserInfoService {
	@Autowired
	private UserInfoRepository infoRepository;
  @Autowired
  private EmailCfg emailCfg;
	public UserInfo login(UserInfo user) {
		UserInfo info = infoRepository.findByUemailAndUpass(user.getUemail(), user.getUpass());
    String role= info.getRole();
    if(role == "admin")
		if(info==null)
			return new UserInfo();
		return info;
	}
  public String save(UserInfo user) {
    if(user==null) throw new NullPointerException("user is null");
    user.setRole("user");
    infoRepository.save(user);
    emailCfg.sendMail(user.getUemail(),"Welcome to project management" ,"Hello,\n\nWelcome to %s! We're thrilled to have you on board.\n\n%s is a state-of-the-art project management platform designed to streamline collaboration and enhance productivity. Whether you're working on personal tasks or managing a team, our platform has got you covered.\n\nThank you for choosing %s for your project management needs. If you have any questions or need assistance, feel free to reach out to our support team.\n\nHappy managing!\n\nBest regards,\nThe %s Team");
    return "saved";
  }
}
