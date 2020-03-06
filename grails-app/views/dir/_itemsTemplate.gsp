<ul class="items-list">


	<div class="item-dropdown">
		<g:link action="show" controller="uploadFile" id="${item.id}">${item.name}</g:link>
		<span>ᐁ</span>
		<li class="items-list-item" id="${item.id}">
			<button class="download" id="${item.id}">download</button>
			<g:form resource="${item}" method="DELETE">
	            <button class="delete" type="submit" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >delete</button>
	        </g:form> 
		</li>
	</div>
</ul>