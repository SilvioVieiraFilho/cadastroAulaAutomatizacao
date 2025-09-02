Feature: Funcionalidade register

  Scenario: Registrar um novo usuário com dados válidos
    Given que o navegador está aberto
    And o usuário acessa a URL "https://front.serverest.dev/login"
    When o usuário clica no botão "btn-link"
    And preenche o campo de nome com "silvio rodrigues Vieira Filho"
    And preenche o campo de email com "nino_silvio@gmail.com"
    And preenche o campo de senha com "12345"
    And clica no botão "btn-primary"
    Then você acessara a pagina logado



