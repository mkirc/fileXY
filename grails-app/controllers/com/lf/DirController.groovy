package com.lf

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import groovy.xml.MarkupBuilder


class DirController {

    DirService dirService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond dirService.list(params), model:[dirCount: dirService.count()]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def show(Long id) {
        respond dirService.get(id)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def create() {
        Dir subDir = new Dir(params)
        try {
            def p = dirService.get(params.dir.id)
            subDir.parent = p
        }
        catch (MissingPropertyException) {

        }
        
        respond subDir
    }    
    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def listSubDirs(Long id) {
        def d = dirService.get(id)
        render(template: "subDirsTemplate", var: "subDir", collection: d.subDirs)
    }
    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def listItems(Long id) {
        def d = dirService.get(id)
        render(template: "itemsTemplate", var: "item", collection: d.items)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def save(Dir dir) {
        if (dir == null) {
            notFound()
            return
        }

        try {
            try {
                dir.parent.exists()
                dir.parent.subDirs.add(dir)
            }
            catch (NullPointerException) {

            }

            dirService.save(dir)
        } catch (ValidationException e) {
            respond dir.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dir.label', default: 'Dir'), dir.id])
                redirect action:"index"
            }
            '*' { respond dir, [status: CREATED] }
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def edit(Long id) {
        respond dirService.get(id)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def update(Dir dir) {
        if (dir == null) {
            notFound()
            return
        }

        try {

            dirService.save(dir)
        } catch (ValidationException e) {
            respond dir.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'dir.label', default: 'Dir'), dir.id])
                redirect dir
            }
            '*'{ respond dir, [status: OK] }
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        dirService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'dir.label', default: 'Dir'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dir.label', default: 'Dir'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
