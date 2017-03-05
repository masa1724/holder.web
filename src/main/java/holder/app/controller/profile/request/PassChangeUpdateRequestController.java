package holder.app.controller.profile.request;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import holder.app.controller.AbstractController;
import holder.app.controller.profile.PassChangeController;
import holder.app.controller.profile.form.PassChangeForm;
import holder.app.controller.profile.form.ProfileForm;
import holder.app.session.SessionKey;
import holder.app.session.UserInfo;
import holder.app.utils.message.MessageId;
import holder.domain.model.User;
import holder.domain.service.holder.profile.PassChangeService;

@Controller
public class PassChangeUpdateRequestController extends AbstractController {
	@Autowired
	private PassChangeService service;
	
    /**
     * 送信ボタン押下時、パスワード変更処理を行う。
     * @param form
     * @param bindingResult
     * @param attributes
     * @return
     * @throws IOException 
     * @throws DataAccessException 
     */
	@RequestMapping(value = "/profile/passchange/update", method = RequestMethod.POST)
	public String index2(@Validated @ModelAttribute("form") PassChangeForm form,
					     @ModelAttribute(SessionKey.USER_INFO) UserInfo userInfo,
					     BindingResult bindingResult,
						 RedirectAttributes attributes) throws DataAccessException, IOException {
			
		// 入力チェック
		if (bindingResult.hasErrors()){
			return getFlowURL(PassChangeController.class);
		}
		
		// パスワードを更新する
		service.updatePassword(userInfo.getSystemUserId(), form.getNewPassword());
		
		// 画面へ成功メッセージを表示
		bindingResult.rejectValue(ProfileForm.PASSWORD, MessageId.PASSCHANGE_SUCCESS);
		
		return getFlowURL(PassChangeController.class);
	}
}