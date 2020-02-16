<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'dir.label', default: 'Dir')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <asset:javascript src="jquery-3.3.1.min.js"/>

    </head>
    <body>
        <a href="#list-dir" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-dir" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            %{-- <f:table collection="${dirList}"/> --}%
            <g:each in="${dirList}" var="it">
                <g:if test="${it.parent == null}">
                    <ul class='dir-list'>
                        <li class='dir-list-item' id="${it.id}">
                            <g:link action="show" id="${it.id}">${it.name}</g:link>
                            <button class='ls' id="${it.id}">ls</button>
                            <button class='mkdir' id="${it.id}">mkdir</button>
                            <button class='addFile' id="${it.id}">add File</button>
                            <div></div>
                        </li>
                    </ul>
                </g:if>
            </g:each>
%{--             <div class="pagination">
                <g:paginate total="${dirCount ?: 0}" />
            </div> --}%
        </div>
    </body>
</html>