package com.lf

class Dir {

	String name
	Dir parent
	List<UploadFile> items
    Set<Dir> subDirs

	static hasMany = [items: UploadFile, subDirs: Dir]
	static hasOne = [parent: Dir]
	static mappedBy = [subDirs: 'parent']
    static mapping = {
        subDirs cascade: 'delete'
        items cascade: 'all-delete-orphan'
    }

    static constraints = {
    	name nullable: false, blank: false
    	subDirs nullable: true
    	parent nullable: true
    	items nullable: true
    }

    String toString() {
    	name
    }
}
