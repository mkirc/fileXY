package com.lf

import com.lf.User
import com.lf.Role
import com.lf.UserRole

class BootStrap {

    def init = { servletContext ->
    	    new Role(authority: 'ROLE_USER').save()

	    	def userRole = new Role(authority: 'ROLE_USER').save(flush: true)
	    	def testUser = new User(username: 'q', fullname:'jomike dafunq', password:'q').save()
	    	UserRole.create testUser, userRole, true

	    	assert User.count() ==1
    }
    def destroy = {
    }
}
