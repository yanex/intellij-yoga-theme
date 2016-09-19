import java.io.File
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

val THEME_NAME = "Yoga"
val THEME_FILE_NAME = "$THEME_NAME.icls"

val baseDir = File(".")
val themeFile = File(baseDir, THEME_FILE_NAME)
val targetJar = File(baseDir, "$THEME_NAME.jar")

if (targetJar.exists()) targetJar.deleteRecursively()

val colorsScheme = """
""".trimIndent()

ZipOutputStream(targetJar.outputStream()).use { zip ->
    zip.putNextEntry(ZipEntry("IntelliJ IDEA Global Settings"))
    zip.closeEntry()

    zip.putNextEntry(ZipEntry("options/colors.scheme.xml"))
    zip.write("""
        <?xml version="1.0" encoding="UTF-8"?>
        <application>
            <component name="EditorColorsManagerImpl">
                <option name="USE_ONLY_MONOSPACED_FONTS" value="true" />
                <global_color_scheme name="Yoga" />
            </component>
        </application>""".trimIndent().toByteArray())
    zip.closeEntry()

    zip.putNextEntry(ZipEntry("colors/$THEME_FILE_NAME"))
    zip.write(themeFile.readBytes())
    zip.closeEntry()
}