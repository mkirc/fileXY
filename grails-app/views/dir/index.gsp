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
            <f:table collection="${dirList}"/>
            <g:each in="${dirList}" var="it">
                <g:if test="${it.parent == null}">
                    %{-- <g:link action="listSubDirs" id="${it.id}">${it.name}</g:link> --}%
                    <ul class='dirs'>
                        <li id="${it.id}"><a>${it.name} </a><button class='ls' id="${it.id}">ls</button></li>

                        %{-- <g:createLink action="listSubDirs" id="${it.id}">${it.name}</g:createLink> --}%
                    </ul>
                    
                    <script type="text/javascript">
                    $(document).ready(function() {
                        $('#${it.id}').click(function() {
                            $.ajax({
                                url:"${g.createLink(action:'listSubDirs')}",
                                data: {id: "${it.id}"},
                                success: function(resp) {
                                    $('#${it.id}').append(resp);
                                    // console.log(resp);
                                },
                                error: function(request, status, error) {
                                    alert(error);
                                },

                            });

                        });
                    });

                </script>
                </g:if>
            </g:each>


            <!-- <ul id='dir-list'>
                <g:each in="${dirList}" var="it">
                    <li><span class="caret"><g:link action="show" id="${it.id}">${it.name}</g:link></span>
                    <ul class="nested">
                        <g:each in="${it.subDirs}" var="sd">
                            <li><span class="caret"><g:link action="show" id="${sd.id}">${sd.name}</g:link></span>
                        </g:each></li>
                </g:each></li>
                </ul>
            </ul> -->
            <div class="pagination">
                <g:paginate total="${dirCount ?: 0}" />
            </div>
        </div>
    </body>
</html>