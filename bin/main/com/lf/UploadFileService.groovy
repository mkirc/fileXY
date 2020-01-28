package com.lf

import grails.gorm.services.Service

@Service(UploadFile)
interface UploadFileService {

    UploadFile get(Serializable id)

    List<UploadFile> list(Map args)

    Long count()

    void delete(Serializable id)

    UploadFile update(Serializable id, Long version, byte[] featuredBytes, String featuredContentType) 

    UploadFile save(UploadFile uploadFile)

}