package holder.app.util;
public class ValidatorMessages {
	/** {0}が選択されていません。 */
	public static final String ASSERTFALSE = "AssertFalse";
	/** must be true */
	public static final String ASSERTTRUE = "AssertTrue";
	/** must be less than ${inclusive == true ? 'or equal to ' : ''}{value} */
	public static final String DECIMALMAX = "DecimalMax";
	/** must be greater than ${inclusive == true ? 'or equal to ' : ''}{value} */
	public static final String DECIMALMIN = "DecimalMin";
	/** {0}は半角数字を入力してください。 */
	public static final String DIGITS = "Digits";
	/** {0}に未来日は指定できません。 */
	public static final String FUTURE = "Future";
	/** {0}は{1}文字以下で入力してください。 */
	public static final String MAX = "Max";
	/** {0}は{1}文字以上で入力してください。 */
	public static final String MIN = "Min";
	/** {0}は{2}文字以上 {1}文字以下で入力してください。 */
	public static final String SIZE = "Size";
	/** {0}が入力されていません。 */
	public static final String NOTNULL = "NotNull";
	/** {0}が入力されていません。 */
	public static final String NOTEMPTY = "NotEmpty";
	/** {0}はnull */
	public static final String NULL = "Null";
	/** {0}に過去日は指定できません。 */
	public static final String PAST = "Past";
	/** must match "{regexp}" */
	public static final String PATTERN = "Pattern";
	/** パスワードと確認用パスワードが一致しません。 */
	public static final String CONFIRMPASSWORD = "ConfirmPassword";
	/** ファイルが選択されていません。 */
	public static final String NOTSELECTEDFILE = "NotSelectedFile";
	/** このEmailは既に利用されています。 */
	public static final String ALREADYUSEDEMAIL = "AlreadyUsedEmail";
	/** 不正な値が入力されました。 */
	public static final String ILLEGALVALUEINPUT = "IllegalValueInput";
	/** ファイルアップロード中にエラーが発生しました。 */
	public static final String FILE_UPLOADERROR = "File.UploadError";
	/** ファイルアップロードが完了しました。 */
	public static final String FILE_UPLOADSUCCESS = "File.UploadSuccess";
	/** ログインに失敗しました。 */
	public static final String LOGIN_FAILED = "Login.Failed";
	/** パスワード更新に成功しました。 */
	public static final String PASSCHANGE_SUCCESS = "PassChange.SUCCESS";
}
