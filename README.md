#Spring Boot Trainning Repository

@Valid: Executa validação pelo String Boot, evitando que o dado seja enviado ao servidor sem
        validação, gerando erro 404 (BAD REQUEST) ao invpés de 500 (INTERNAL SERVEr ERROR).
        Uma Lista de Erros na classe de traamento de erros, é necessária para validação de vários campos de uma classe.

Arquivo ValidationMessages.properties:
        Permite a modificação das mensagens automáticas geradas pelo SpringBoot.
        Necessário para que não apareçam nomes em camelCase no FrontEnd por exemplo.

        Veirifcar na documentação de javax.validation.contrains, as possíveis validações
        para acresentar no arquivo.

