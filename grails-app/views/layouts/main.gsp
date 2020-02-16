<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="fileXY"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="thump.png" type="image/x-ico"/>

    <asset:stylesheet src="application.css"/>


    <g:layoutHead/>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-dark navbar-static-top" role="navigation">
    <a class="navbar-brand" href="/#"><asset:image src="fileXY.svg" alt="fileXY" height="100" width="450"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" aria-expanded="false" style="height: 0.8px;" id="navbarContent">
        <ul class="nav navbar-nav ml-auto">
            <g:pageProperty name="page.nav"/>
            <sec:ifLoggedIn>
              <li class="nav-item dropdown">
                  <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                    <sec:loggedInUserInfo field='fullname'/>
                  </a>
                  <div class="dropdown-menu navbar-dark">
                    <g:form controller="logout">
                      <g:submitButton class="dropdown-item navbar-dark color-light" name="Submit" value="Logout" style="color:gray" />
                    </g:form>
                  </div>
              </li>
            </sec:ifLoggedIn>
        </ul>
    </div>

</nav>

<div class="container">
    <g:layoutBody/>
</div>

<div class="footer row" role="contentinfo">
    <div class="col">
        <a href="http://lecker-fleischwurst.de" target="_blank">
            <asset:image src="thump.png" alt="Lecker, Fleischwurst" class="float-left"/>
        </a>
        <strong class="centered"><a href="http://lecker-fleischwurst.de" target="_blank">Lecker</a></strong>
        <p>Visit our very much relevant Blawg on <a href="http://lecker-fleischwurst.de" target="_blank">lecker-fleischwurst.de</a>.</p>

    </div>

    <div class="col">
        <strong class="centered"></strong>
        <p>This is a learning project. </p>
        <p>Go easy on me, huh?</p>

    </div>


    <div class="col">
        <a href="https://xkcd.com/" target="_blank">
            <asset:image src="xkcd.png" alt="XKCD" class="float-left"/>
        </a>
        <strong class="centered"><a href="https://xkcd.com/" target="_blank">Read some Comics</a></strong>
        <p>Use your free time and <a href="https://xkcd.com/" target="_blank">Read some Webcomics</a>.</p>
    </div>
</div>


<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>

<asset:javascript src="application.js"/>
<asset:javascript src="jquery-ui.min.js"/>
<asset:javascript src="scripts.js"/>


</body>
</html>