import com.lf.UserPasswordEncoderListener
import com.lf.CustomUserDetailsService
// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener)
    userDetailsService(CustomUserDetailsService)
}
