package com.lf

import com.lf.User
import com.lf.Role
import com.lf.UserRole
import grails.gorm.transactions.Transactional

class BootStrap {

    def init = { servletContext ->
    	    // new Role(authority: 'ROLE_USER').save()
    	    createUser() 
    	}

    @Transactional
    def createUser() {


	    	def userRole = Role.findOrSaveByAuthority(Role.USER)

	    	def testUser = new User(username: 'q'
	    		, enabled: true
	    		, fullname:'jomike dafunq'
	    		, password:'q')
	    	testUser.save(flush: true)
	    	
	    	UserRole.create(testUser, userRole).save(flush: true)

	    	assert User.count() ==1
    }
    def destroy = {
    }
}
