package br.com.smartclinmed.web.acessos;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UsuarioPerfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true)
	private String descricao;
	/*
	 * @JsonIgnore
	 * 
	 * @ManyToMany(fetch = FetchType.EAGER)
	 * 
	 * @JoinTable(name = "UsuarioPerfilPermissoes", joinColumns = @JoinColumn(name =
	 * "usuarioperfil_id"), inverseJoinColumns = @JoinColumn(name = "permissao_id"))
	 * 
	 * @Fetch(FetchMode.SUBSELECT) private List<Permissao> permissoes = new
	 * ArrayList<>();
	 */
	private LocalDateTime dtInclusao;
	private LocalDateTime dtAlteracao;

	public UsuarioPerfil() {

	}

	public UsuarioPerfil(Integer id, String descricao, LocalDateTime dtInclusao, LocalDateTime dtAlteracao) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dtInclusao = dtInclusao;
		this.dtAlteracao = dtAlteracao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/*
	 * public List<Permissao> getPermissoes() { return permissoes; }
	 * 
	 * public void setPermissoes(List<Permissao> permissoes) { this.permissoes =
	 * permissoes; }
	 * 
	 * public void addPermissao(Permissao permissao) { permissoes.add(permissao); }
	 */
	public LocalDateTime getDtInclusao() {
		return dtInclusao;
	}

	public void setDtInclusao(LocalDateTime dtInclusao) {
		this.dtInclusao = dtInclusao;
	}

	public LocalDateTime getDtAlteracao() {
		return dtAlteracao;
	}

	public void setDtAlteracao(LocalDateTime dtAlteracao) {
		this.dtAlteracao = dtAlteracao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioPerfil other = (UsuarioPerfil) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
