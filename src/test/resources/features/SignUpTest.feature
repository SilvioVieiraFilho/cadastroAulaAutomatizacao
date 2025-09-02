Feature: Funcionalidade Login

  Scenario: Validar campo e-mail

    Given que o navegador esteja aberto
    And o cliente acessa a URL "https://front.serverest.dev/login"
    And preencha o campo digite seu e-mail incorreto "teste"
    And  preencha o campo senha "teste"
    When clicar no botão entrar "btn-primary"
    Then tera que apresentar uma mensagem comunicando que precisa ser um e-mail