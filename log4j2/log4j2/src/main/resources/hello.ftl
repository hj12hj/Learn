<#macro test list >
    <#if list ??>
        <#list list as item>
            ${item.name}
            <@test list=item.children />
        </#list>
    </#if>
</#macro>



<@test list=root.children />