<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="index.css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Concesionario</title>
    </head>

    <body>

        <div class="container mt-4">
            <form action="/coches" method="post">

                <div class="mb-3">
                    <label for="modeloInput" class="form-label">Modelo:</label>

                    <select class="form-select" aria-label="Default select example" id="modeloInput" name="modeloInput" aria-describedby="modeloInputHelp">

                        <#assign opcionSeleccionada="selected">
                        <#if modeloSeleccionado??>
                            <#assign opcionSeleccionada="">
                        </#if>
                        <option ${opcionSeleccionada} value="">Selecciona un modelo</option>
                        <#if modelitos??>
                            <#list modelitos as modelito>
                                <#if modeloSeleccionado?? && modeloSeleccionado==modelito>
                                    <#assign opcionSeleccionada="selected">
                                <#else>
                                    <#assign opcionSeleccionada="">
                                </#if>
                                <option ${opcionSeleccionada} value="${modelito}">${modelito}</option>
                            </#list>
                        </#if>
                    </select>
                    <div id="modeloInputHelp" class="form-text">Selecciona un modelo para filtra.</div>
                </div>

                <button type="submit" class="btn btn-primary">Actualizar</button>
            </form>
        </div>

        <div class="container mt-4">
            <h2 class="text-primary">Listado de Coches</h2>
            <div class="row">
                <#list coches as coche>
                    <div class="col-3">
                        <div class="card shadow">
                            <img src="${coche.imagen}" class="card-img-top" alt="${coche.modelo}">
                            <div class="card-body">
                                <h5 class="card-title">${coche.modelo}</h5>
                                <p class="card-text"><strong>Precio:</strong> ${coche.precio}</p>
                                <p class="card-text"><strong>Color:</strong> ${coche.color}</p>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js" integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq" crossorigin="anonymous"></script>

    </body>
</html>