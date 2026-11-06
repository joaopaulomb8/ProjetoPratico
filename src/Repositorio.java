import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;


public class Repositorio<T extends Entidade> {

    private final Map<Integer, T> dados = new LinkedHashMap<>();
    private int proximoId = 1;


    public T criar(T entidade) {
        entidade.setId(proximoId++);
        dados.put(entidade.getId(), entidade);
        return entidade;
    }


    public List<T> listar() {
        return new ArrayList<>(dados.values());
    }


    public Optional<T> buscarPorId(int id) {
        return Optional.ofNullable(dados.get(id));
    }


    public List<T> buscar(Predicate<T> filtro) {
        return dados.values().stream().filter(filtro).toList();
    }


    public boolean atualizar(T entidade) {
        if (!dados.containsKey(entidade.getId())) {
            return false;
        }
        dados.put(entidade.getId(), entidade);
        return true;
    }


    public boolean deletar(int id) {
        return dados.remove(id) != null;
    }
}
 