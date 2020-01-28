package com.lf

import com.lf.User
import com.lf.Role
import com.lf.UserRole

class BootStrap {

    def init = { servletContext ->
    	    new Role(authority: 'ROLE_USER').save()
    }
    def destroy = {
    }
}
