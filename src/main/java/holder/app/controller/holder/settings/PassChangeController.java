package holder.app.controller.holder.settings;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import holder.app.SessionKey;
import holder.app.controller.AbstractController;
import holder.app.controller.holder.settings.form.PassChangeForm;
import holder.app.util.ValidatorMessages;
import holder.domain.model.User;
import holder.domain.service.holder.settings.PassChangeService;

@Controller
public class PassChangeController extends AbstractController {
	@Autowired
	private PassChangeService service;
	
	/**
	 * パスワード変更画面を表示する。
	 * @param form
	 * @return
	 */
    @RequestMapping(value = "/settings/passchange", method = RequestMethod.GET)
    public String index(@ModelAttribute("form") PassChangeForm form) {
        return "settings/passchange";
    }
    
    /**
     * 送信ボタン押下時、パスワード変更処理を行う。
     * @param form
     * @param bindingResult
     * @param attributes
     * @return
     * @throws IOException 
     * @throws DataAccessException 
     */
	@RequestMapping(value = "/settings/passchange", method = RequestMethod.POST)
	public String index2(@Validated @ModelAttribute("form") PassChangeForm form, 
					     HttpSession session,
						 BindingResult bindingResult,
						 RedirectAttributes attributes) throws DataAccessException, IOException {
		
		User user = (User)session.getAttribute(SessionKey.USER_INFO);
		
		// 入力チェック
		if (bindingResult.hasErrors()){
			return "settings/passchange";
		}
		
		// パスワードを更新する
		service.updatePassword(user.getSystemUserId(), form.getNewPassword());
		
		// 画面へ成功メッセージを表示
		bindingResult.rejectValue(PassChangeForm.PASSWORD, ValidatorMessages.PASSCHANGE_SUCCESS);
		
		return "settings/passchange";
	}
}