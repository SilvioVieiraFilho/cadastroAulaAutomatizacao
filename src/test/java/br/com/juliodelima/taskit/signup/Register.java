package br.com.juliodelima.taskit.signup;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.*;
import org.junit.Assert;
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

    private WebDriver navegador;

    @Before
    public void setUp() {
        // 🛠 Caminho personalizado do Chrome renomeado como google_1232.exe
        String caminhoChrome = "C:/Program Files/Google/Chrome/Application/chrome_123.exe";
        File chromeFile = new File(caminhoChrome);
        if (!chromeFile.exists()) {
            throw new RuntimeException("Chrome personalizado não encontrado no caminho: " + caminhoChrome);
        }

        // 📦 Configurando o WebDriverManager com o executável personalizado
        WebDriverManager.chromedriver()
                .browserBinary(caminhoChrome)
                .setup();

        // ⚙️ Configura opções para o Chrome
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



    @E("preenche o campo de email com {string}")
    public void preencheOCampoDeEmailCom(String id) {
        navegador.findElement(By.id("email")).sendKeys(id);
    }

    @E("preenche o campo de senha com {string}")
    public void preencheOCampoDeSenhaCom(String id) {

        navegador.findElement(By.id("password")).sendKeys(id);
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



}
