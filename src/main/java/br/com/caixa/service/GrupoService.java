package br.com.caixa.service;

import br.com.caixa.data.vo.v1.GrupoVO;
import br.com.caixa.mapper.DozerMapper;
import br.com.caixa.models.Grupo;
import br.com.caixa.models.records.GrupoAlterRecord;
import br.com.caixa.models.records.GrupoRecord;
import br.com.caixa.repositories.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository repository;


    public Grupo create(GrupoRecord grupoRecord){
        Grupo grupo = new Grupo();
        grupo.setName(grupoRecord.name());
        repository.save(grupo);
        return grupo;
    }

    public Grupo findById(Integer id){
        var entity = repository.findById(id).orElseThrow(() -> new RuntimeException(""));
        return entity;
    }

    public List<Grupo> findAll(){
        var entity = repository.findAll();
        return entity;
    }

    public Grupo update(GrupoAlterRecord grupoAlterRecord){
        var entity = repository.findById(grupoAlterRecord.id()).orElseThrow(() -> new RuntimeException(""));
        entity.setName(grupoAlterRecord.name());
        repository.save(entity);
        return entity;
    }

    public void delete(Integer id){
        repository.deleteById(id);
    }


}
