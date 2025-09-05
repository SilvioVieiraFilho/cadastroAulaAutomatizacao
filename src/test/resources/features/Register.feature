Feature: Funcionalidade register

  Scenario: Registrar um novo usuário com dados válidos
    Given que o navegador está aberto
    And o usuário acessa a URL "https://front.serverest.dev/login"
    When o usuário clica no botão cadastrar "btn-link"
    And preenche o campo de nome com "silvio rodrigues Vieira Filho"
    And preenche o campo de email com um email válido
    And preenche o campo de senha com "12345"
    And clica no botão "btn-primary"
    Then você acessara a pagina logado

  Scenario: Registrar um novo usuário com dados válidos administrador
    Given que o navegador está aberto
    And o usuário acessa a URL "https://front.serverest.dev/login"
    When o usuário clica no botão cadastrar "btn-link"
    And preenche o campo de nome com "silvio rodrigues Vieira Filho"
    And preenche o campo de email com um email válido
    And preenche o campo de senha com "12345"
    And clica na checkbox "administrador"
    And clica no botão "btn-primary"
    Then ira exibir a tela da pagina admin

  Scenario: Validar o botão entrar da tela de cadastro
    Given que o navegador está aberto
    And o usuário acessa a URL "https://front.serverest.dev/login"
    And   o usuário clica no botão cadastrar "btn-link"
    When  o usuário clica no botão entrar "btn-link"
    Then o sistema deve apresentar a tela de login

    Scenario: Validar email ja registrado no banco de dados
      Given que o navegador está aberto
      And o usuário acessa a URL "https://front.serverest.dev/login"
      When o usuário clica no botão cadastrar "btn-link"
      And preenche o campo de nome com "silvio rodrigues Vieira Filho"
      And preenche o campo de email com um email ja cadastrado "Teste_testando@gmail.com"
      And preenche o campo de senha com "12345"
      And clica no botão "btn-primary"
      Then você recebera uma mensagem Este email já está sendo usado

  Scenario: Validar campo e-mail

    Given que o navegador esteja aberto
    And o cliente acessa a URL "https://front.serverest.dev/login"
    And clica no botão "btn-link"
    And preencha o campo digite seu e-mail incorreto "teste"
    And preenche o campo de nome com "silvio rodrigues Vieira Filho"
    And  preencha o campo senha "teste"
    When clicar no botão entrar "btn-primary"
    Then tera que exibir uma mensagem comunicando que precisa ser um e-mail