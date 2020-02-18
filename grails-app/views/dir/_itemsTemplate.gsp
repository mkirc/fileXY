<ul class="items-list">
	<li class="items-list-item" id="${item.id}">
		<g:link action="show" controller="uploadFile" id="${item.id}">${item.name}</g:link>
		<g:form resource="${item}" method="DELETE">
            <button class="delete" type="submit" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >delete</button>
        </g:form> 
	</li>
</ul>