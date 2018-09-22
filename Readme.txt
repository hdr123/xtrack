MAVEN INSTALL Driver Sql Server
------------------------------------------------
Agar dapat menambahkan dependency sql server pada POM maven, buka command prompt (cmd)
lalu anda pergi ke direktori lib yang ada di dalam direktori project "xtrack" dengan
menggunakan cmd, contoh "D:\ cd $xtract\lib"
Kemudian ketik perintah dibawah ini:
	mvn install:install-file -Dfile=sqljdbc4-4.0.jar -DgroupId=com.miscrosoft.sqlserver -DartifactId=sqljdbc4 -Dversion=4.0 -Dpackaging=jar