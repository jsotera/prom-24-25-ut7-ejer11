<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="index.css" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hola FreeMarker</title>
</head>

<#assign background="grey">

<#if javi?? && javi.edad gte 18 >
    <#assign background="blue">
</#if>

<body style="background-color: ${background};">
    <h1>${mensaje}</h1>

    <#if personas??>

        <#list personas as personita>

            <#assign fondoPersona="yellow" />
            <#if personita?index % 2 == 0>
                <#assign fondoPersona="white">
            </#if>
            <div style="background-color:${fondoPersona};">
                <h2>${personita?index + 1} ${personita.nombre}</h2>
                <h3>Edad: ${personita.edad} años.</h3>
            </div>
        <#else>
            <div>No hay personas.</div>
        </#list>

    </#if>

</body>
</html>