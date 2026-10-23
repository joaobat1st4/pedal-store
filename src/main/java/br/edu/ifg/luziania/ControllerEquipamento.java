package br.edu.ifg.luziania;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

// Esta é a morada da nossa API. O front-end vai chamar http://localhost:8080/pedais
@Path("/pedais")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ControllerEquipamento {

    // Lista na memória para guardar os pedais temporariamente (finge ser a base de dados)
    private static List<Equipamento> listaPedais = new ArrayList<>();

    // Método GET: Devolve todos os pedais para a Vitrine
    @GET
    public List<Equipamento> listarTodos() {
        return listaPedais;
    }

    // Método POST: Recebe os dados do formulário e guarda na lista
    @POST
    public Response adicionar(Equipamento novoPedal) {
        // Como não temos base de dados, inventamos um ID único baseado na hora
        novoPedal.setId(System.currentTimeMillis());

        // Garante que o estado inicial é sempre DISPONIVEL
        if (novoPedal.getStatus() == null || novoPedal.getStatus().isEmpty()) {
            novoPedal.setStatus("DISPONIVEL");
        }

        listaPedais.add(novoPedal);

        // Devolve uma resposta de sucesso (Código 201 - Created)
        return Response.status(Response.Status.CREATED).entity(novoPedal).build();
    }

    // Metodo que responde ao CORS Preflight para a rota principal e qualquer sub-rota (ex: /123 e /123/reservar)
    @OPTIONS
    @Path("{path: .*}")
    public Response preflightTodasAsRotas() {
        return Response.ok().build();
    }

    // Metodo DELETE: Apaga o pedal da memória
    @DELETE
    @Path("/{id}")
    public Response excluir(@PathParam("id") Long id) {
        boolean removido = listaPedais.removeIf(p -> p.getId().equals(id));
        if (removido) {
            return Response.noContent().build(); // Retorna 204 (Sucesso, sem conteúdo)
        }
        return Response.status(Response.Status.NOT_FOUND).build(); // Retorna 404 se não encontrar
    }

    // Metodo PUT: Atualiza o status do pedal para RESERVADO
    @PUT
    @Path("/{id}/reservar")
    public Response reservar(@PathParam("id") Long id) {
        for (Equipamento p : listaPedais) {
            if (p.getId().equals(id)) {
                p.setStatus("RESERVADO");
                return Response.ok(p).build(); // Retorna o pedal atualizado
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
