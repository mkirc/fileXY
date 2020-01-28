package com.lf

import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

class FeaturedBytesCommand implements Validateable {
    MultipartFile featuredBytesFile
    Long id
    Integer version

    static constraints = {
        id nullable: false
        version nullable: false
        featuredBytesFile  validator: { val, obj ->
            if ( val == null ) {
                return false
            }
            if ( val.empty ) {
                return false
            }

        }
    }
}