package com.lf

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import org.springframework.web.multipart.MultipartFile
import grails.plugin.springsecurity.annotation.Secured


class UploadFileController {

    UploadFileService uploadFileService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond uploadFileService.list(params), model:[uploadFileCount: uploadFileService.count()]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def show(Long id) {
        respond uploadFileService.get(id)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def uploadFeaturedBytes(FeaturedBytesCommand cmd) {
        if (cmd == null) {
            notFound()
            return
        }

        if (cmd.hasErrors()) {
            respond(cmd.errors, model: [uploadFile: cmd], view: 'edit')
            return
        }

        UploadFile uploadFile = uploadFileService.update(cmd.id,
                cmd.version,
                cmd.featuredBytesFile.bytes,
                cmd.featuredBytesFile.contentType)
        if (uploadFile == null) {
            notFound()
            return
        }

        if (uploadFile.hasErrors()) {
            respond(uploadFile.errors, model: [uploadFile: uploadFile], view: 'edit')
            return
        }

        Locale locale = request.locale
        flash.message = crudMessageService.message(CRUD.UPDATE, domainName(locale), cmd.id, locale)
        redirect uploadFile
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def create() {
        respond new UploadFile(params)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def download(Long id) {

        UploadFile downloadFile = uploadFileService.get(id)


        if (downloadFile || downloadFile.featuredBytes != null) {

            byte[] bytes = downloadFile.featuredBytes
            response.setContentType("application/octet-stream")
            response.setHeader("Content-disposition", "filename=${downloadFile.name}")
            response.outputStream
            response.outputStream << bytes
        }
        else {
            flash.message = "Download failed; ERR: ${params}"
            redirect (action:'index')
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def save(UploadFile uploadFile) {
        if (uploadFile == null) {
            notFound()
            return
        }

        try {
            MultipartFile file = request.getFile('featuredBytes')

            if (file != null) {
                uploadFile.name = file.getOriginalFilename()

                if (uploadFile.name.length() != 0) {
                    uploadFile.extension = uploadFile.name.substring(uploadFile.name.lastIndexOf(".") + 1)
                    uploadFile.featuredContentType = file.getContentType()
                } else {
                    uploadFile.name = uploadFile.description
                    uploadFile.featuredContentType = 'text/plain'
                }

            }

            uploadFileService.save(uploadFile)
        } catch (ValidationException e) {
            respond uploadFile.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'uploadFile.label', default: 'UploadFile'), uploadFile.name])
                redirect action: 'index', controller: 'dir'       
            }
            '*' { respond uploadFile, [status: CREATED] }
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def edit(Long id) {
        respond uploadFileService.get(id)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def featuredImage(Long id) {
    UploadFile uploadFile = uploadFileService.get(id)
    if (!uploadFile || uploadFile.featuredBytes == null) {
        notFound()
        return
    }
    render file: uploadFile.featuredBytes,
        contentType: uploadFile.featuredContentType
}
    
    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def update(UploadFile uploadFile) {
        if (uploadFile == null) {
            notFound()
            return
        }

        try {
            uploadFileService.save(uploadFile)
        } catch (ValidationException e) {
            respond uploadFile.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'uploadFile.label', default: 'UploadFile'), uploadFile.id])
                redirect uploadFile
            }
            '*'{ respond uploadFile, [status: OK] }
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        uploadFileService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'uploadFile.label', default: 'UploadFile'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'uploadFile.label', default: 'UploadFile'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}