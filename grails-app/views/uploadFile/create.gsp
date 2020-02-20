<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'uploadFile.label', default: 'UploadFile')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-uploadFile" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-uploadFile" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.uploadFile}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.uploadFile}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.uploadFile}" method="POST" enctype="multipart/form-data" >
                <fieldset class="form">
                    <f:all bean="uploadFile" except="featuredContentType, name, extension"/>
                </fieldset>
                <fieldset>
                    %{-- <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /> --}%
                    <button class='create' type='submit'>create</button>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
