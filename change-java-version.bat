@echo off
echo Alterando para Java 21...

:: Define JAVA_HOME no sistema (requer admin)
setx JAVA_HOME "C:\Program Files\Java\jdk-21" /M

:: Atualiza o Path para refletir a nova versão
setx PATH "C:\Program Files\Java\jdk-21\bin;%PATH%" /M

:: Força o sistema a reconhecer a mudança
echo JAVA_HOME atualizado para:
echo %JAVA_HOME%
java -version

pause

:: Você pode criar mais um com o conteúdo abaixo para outras versões:
:: @echo off
:: echo Alterando para Java 11...
:: 
:: Define JAVA_HOME no sistema (requer admin)
:: setx JAVA_HOME "C:\Program Files\Java\jdk-11" /M
:: 
:: Atualiza o Path para refletir a nova versão
:: setx PATH "C:\Program Files\Java\jdk-11\bin;%PATH%" /M
:: 
:: Força o sistema a reconhecer a mudança
:: echo JAVA_HOME atualizado para:
:: echo %JAVA_HOME%
:: java -version
:: 
:: pause