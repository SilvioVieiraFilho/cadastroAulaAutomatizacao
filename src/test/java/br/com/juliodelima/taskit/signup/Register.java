package br.com.juliodelima.taskit.signup;

import com.github.javafaker.Faker;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class Register {
    private Faker faker = new Faker();
    private String emailFake;
    private WebDriver navegador = BaseTest.getDriver();

    @Before
    public void setUp() {
        String caminhoChrome = "C:/Program Files/Google/Chrome/Application/chrome_123.exe";
        File chromeFile = new File(caminhoChrome);
        if (!chromeFile.exists()) {
            throw new RuntimeException("Chrome personalizado não encontrado no caminho: " + caminhoChrome);
        }

        WebDriverManager.chromedriver().browserBinary(caminhoChrome).setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary(caminhoChrome);
        options.addArguments("--start-maximized");

        navegador = new ChromeDriver(options);
    }


    @After
    public void tearDown() {
        if (navegador != null) {
            navegador.quit();
        }
    }

    @Dado("que o navegador está aberto")
    public void queONavegadorEstaAberto() {
        // Navegador já está aberto no Before
    }

    @E("o usuário acessa a URL {string}")
    public void oUsuarioAcessaAURL(String url) {
        navegador.get(url);
    }

    @Quando("o usuário clica no botão {string}")
    public void oUsuarioClicaNoBotao(String botaoClasse) {
        navegador.findElement(By.className(botaoClasse)).click();
    }


    @E("preenche o campo de nome com {string}")
    public void preencheOCampoDeNomeCom(String nome) {
        WebDriverWait wait = new WebDriverWait(navegador, Duration.ofSeconds(10));
        WebElement campoNome = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("nome")));
        campoNome.sendKeys(nome);
    }


    @E("preenche o campo de email com um email válido")
    public void preencheOCampoDeEmailCom() {
        Faker faker = new Faker();
        emailFake = faker.internet().emailAddress();
        navegador.findElement(By.id("email")).sendKeys(emailFake);
        System.out.println("Email gerado: " + emailFake);
    }


    @E("preenche o campo de senha com {string}")
    public void preencheOCampoDeSenhaCom(String id) {

        navegador.findElement(By.id("password")).sendKeys(id);
    }

    @E("clica na checkbox {string}")
    public void clicaNocheckbox(String checkbox) {
        navegador.findElement(By.id(checkbox)).click();
    }

    @E("clica no botão {string}")
    public void clicaNoBotao(String botaoClasse) {
        navegador.findElement(By.className(botaoClasse)).click();
    }


    @Então("você acessara a pagina logado")
    public void voceAcessaraAPaginaLogado() {
        WebDriverWait wait = new WebDriverWait(navegador, Duration.ofSeconds(10));
        // Usando o seletor pelo atributo data-testid
        WebElement elemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='pesquisar']")));
        Assert.assertTrue(elemento.isDisplayed());
    }

    @Então("ira exibir a tela da pagina admin")
    public void iraExibirATelaDaPaginaAdmin() {
        WebDriverWait wait = new WebDriverWait(navegador, Duration.ofSeconds(10));

        // Espera até que o texto esteja presente no elemento <p class="lead">
        WebElement mensagem = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.lead"))
        );

        String texto = mensagem.getText();
        Assertions.assertEquals(
                "Este é seu sistema para administrar seu ecommerce.",
                texto
        );

    }
}
