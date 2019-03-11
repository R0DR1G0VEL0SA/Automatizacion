package LinkedIn.DataDriven;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {

	public void metodoLogin() throws IOException {
		System.out.println("Inicio: TestCase #1!");
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		String expectedTitle = "LinkedIn";
		String actualTitle = "";
		File data = new File("data.txt");
		FileReader fr;

		try {

			fr = new FileReader(data);
			BufferedReader br = new BufferedReader(fr);
			String linea = "";

			while ((linea = br.readLine()) != null) {
				try {
					String[] datos = linea.split(",");
					driver.get("https://www.linkedin.com/");
					WebElement txtName = driver.findElement(By.id("login-email"));
					txtName.sendKeys(datos[0]);
					WebElement txtPassword = driver.findElement(By.id("login-password"));
					txtPassword.sendKeys(datos[1]);
					WebElement btnIngresar = driver.findElement(By.xpath("//*[@id=\"login-submit\"]"));
					btnIngresar.click();

					actualTitle = driver.getTitle();

					if (actualTitle.contentEquals(expectedTitle)) {
						JOptionPane.showMessageDialog(null, "Bienvenido, a " + actualTitle);
						driver.quit();
					}

					else {

						WebElement validacion = driver.findElement(By.id("error-for-password"));

						if (validacion.isDisplayed()) {

							JOptionPane.showMessageDialog(null, "Inicio de Sesión - Fallido");
							driver.quit();
						}

					}

				} catch (NoSuchElementException e) {
					// TODO: handle exception
				}
			}

		} catch (NoSuchElementException e) {
			// TODO: handle exception
		}

	}
}
