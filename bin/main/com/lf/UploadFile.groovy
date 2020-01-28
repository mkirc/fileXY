package com.lf

class UploadFile {

	String name
	byte[] featuredBytes
	String featuredContentType
	String description
	String extension
	Date dateCreated
	Date lastUpdated

    static constraints = {
    	name nullable: false, blank: false
    	extension nullable: false
    	description nullable:true, blank:true
    	featuredBytes nullable: true
    	featuredContentType nullable: true
    }

    static mapping = {
    	featuredBytes column: "featured_Bytes", sqlType: "longblob"

    }
}
