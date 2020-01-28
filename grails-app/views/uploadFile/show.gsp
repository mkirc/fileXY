<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'uploadFile.label', default: 'UploadFile')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-uploadFile" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                <li><g:link action="download" class="download" id="${this.uploadFile.id}"><g:message code="
                    default.button.edit.label" default="Download" /></g:link></li>
            </ul>
        </div>
        <div id="show-uploadFile" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <h1><f:display bean="uploadFile" property="name"/></h1>
            <g:if test="${'image' in this.uploadFile.featuredContentType.tokenize('/')}">
                <img src="<g:createLink controller="uploadFile" action="featuredImage" id="${this.uploadFile.id}"/>" width="400"/>
            </g:if>
            <g:if test="${'text' in this.uploadFile.featuredContentType.tokenize('/')}">
                <p src="<g:createLink controller="uploadFile" action="featuredImage" id="${this.uploadFile.id}"/>"/>
            </g:if>
            <g:form resource="${this.uploadFile}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.uploadFile}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
