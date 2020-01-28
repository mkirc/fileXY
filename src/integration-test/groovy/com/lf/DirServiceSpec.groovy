package com.lf

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DirServiceSpec extends Specification {

    DirService dirService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Dir(...).save(flush: true, failOnError: true)
        //new Dir(...).save(flush: true, failOnError: true)
        //Dir dir = new Dir(...).save(flush: true, failOnError: true)
        //new Dir(...).save(flush: true, failOnError: true)
        //new Dir(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //dir.id
    }

    void "test get"() {
        setupData()

        expect:
        dirService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Dir> dirList = dirService.list(max: 2, offset: 2)

        then:
        dirList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        dirService.count() == 5
    }

    void "test delete"() {
        Long dirId = setupData()

        expect:
        dirService.count() == 5

        when:
        dirService.delete(dirId)
        sessionFactory.currentSession.flush()

        then:
        dirService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Dir dir = new Dir()
        dirService.save(dir)

        then:
        dir.id != null
    }
}
