package br.com.senactestes.mockitoclasses;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CadastrarPessoaTeste {
	
//1.CRIAÇÃO DO CENÁRIO
	//1.1 INSTRUMENTAÇÃO PARA O MOCK:
		//1.1.1 INSERÇÃO DO DESCRITOR DO MOCK
	
@Mock
private ApiDosCorreios apiDosCorreios;

//1.1.2 INJEÇÃO DE DEPENDÊNCIA DA REGRA DE NEGÓCIO A SER TESTADA
@InjectMocks
private CadastrarPessoa cadastrarPessoa;
	
@Test
void cadastrarPessoa() {
	//1.2 INSERÇÃO DOS DADOS A SEREM MOCKADOS
	//DADOS FICTICIOS DO OBJETO QUE EU ESTOU MOCANDO
	DadosLocalizacao dadosLocalizacao = new DadosLocalizacao("SP", "Atibaia", "Rua Antoni Massoni", "Casa", "Nova Floresta");
		
	//1.3 EXECUÇÃO DO MOCK
	Mockito.when(apiDosCorreios.buscaDadosCep(anyInt())).thenReturn(dadosLocalizacao);
	
	Pessoa Bia = cadastrarPessoa.cadastrarPessoa("Bia", "123456", LocalDate.of(1999,10,20), 23071190);
	
	
	//EXECUÇÃO E ANÁLISE (ASSERTS) DO TESTE
	DadosLocalizacao enderecoBia = Bia.getEndereco();
	assertEquals(dadosLocalizacao.getBairro(), enderecoBia.getBairro());
	assertEquals(dadosLocalizacao.getCidade(), enderecoBia.getCidade());
	assertEquals(dadosLocalizacao.getUf(), enderecoBia.getUf());
	}
}
