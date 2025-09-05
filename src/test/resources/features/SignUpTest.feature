Feature: Funcionalidade Login

  Scenario: Validar campo e-mail

    Given que o navegador esteja aberto
    And o cliente acessa a URL "https://front.serverest.dev/login"
    And preencha o campo digite seu e-mail incorreto "teste"
    And  preencha o campo senha "teste"
    When clicar no botão entrar "btn-primary"
    Then tera que apresentar uma mensagem comunicando que precisa ser um e-mail


  Scenario: Autenticar usuário com email e senha válidos usuario

    Given que o navegador esteja aberto
    And o cliente acessa a URL "https://front.serverest.dev/login"
    And preencha o campo com seu valido e-mail "Teste_testando@gmail.com"
    And  preencha o campo senha "123456"
    When clicar no botão entrar "btn-primary"
    Then deve ser redirecionado para a página principal

  Scenario: Autenticar usuário com email e senha válidos usuario admin

    Given que o navegador esteja aberto
    And o cliente acessa a URL "https://front.serverest.dev/login"
    And preencha o campo com seu valido e-mail "Teste_testadoadm@gmail.com"
    And  preencha o campo senha "123456"
    When clicar no botão entrar "btn-primary"
    Then ira aparecer a tela da pagina admin

  Scenario: Autenticar usuário com email e senha inválidos

    Given que o navegador esteja aberto
    And o cliente acessa a URL "https://front.serverest.dev/login"
    And preencha o campo com seu  e-mail invalido "Teste_emailerrado@bol.com"
    And  preencha o campo senha "123456"
    When clicar no botão entrar "btn-primary"
    Then deve exibir a mensagem de email e ou senha invalidos

