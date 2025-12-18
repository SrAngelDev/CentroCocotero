# Centro Cocotero - Tu tienda online de confianza :)
![Logo](https://github.com/user-attachments/assets/df112618-a3d2-490f-81f7-50e180fcb214)

Centro Cocotero es un proyecto de una tienda online hecha en Java + Springboot + Pebble Templates

## ⚙️ Configuración Inicial

### 1. Clonar el repositorio
```bash
git clone https://github.com/SrAngelDev/CentroCocotero.git
cd CentroCocotero
```

### 2. Configurar claves secretas

Crea el archivo `src/main/resources/application-local.properties` (ya existe como ejemplo):

```properties
# STRIPE - Obtén tus claves en https://dashboard.stripe.com/test/apikeys
stripe.api.key=sk_test_TU_CLAVE_SECRETA_AQUI
stripe.public.key=pk_test_TU_CLAVE_PUBLICA_AQUI

# EMAIL (SMTP) - Para Gmail, genera una contraseña en https://myaccount.google.com/apppasswords
spring.mail.username=tu-email@gmail.com
spring.mail.password=tu-password-de-aplicacion-aqui
app.mail.from=tu-email@gmail.com
```

> ⚠️ **Importante:** El archivo `application-local.properties` está en `.gitignore` y NO se subirá a Git

### 3. Iniciar la aplicación
```bash
./gradlew bootRun
```

La aplicación estará disponible en: **http://localhost:7070**

## 👥 Usuarios de Prueba

La aplicación se inicializa automáticamente con los siguientes usuarios para pruebas:

### Administrador
- **Email:** `admin@centrococotero.com`
- **Contraseña:** `admin123`
- **Rol:** ADMIN
- **Permisos:** Acceso completo a panel de administración

### Moderador
- **Email:** `moderador@centrococotero.com`
- **Contraseña:** `mod123`
- **Rol:** MODERATOR
- **Permisos:** Permisos de moderación

### Usuarios Normales
1. **Juan Pérez**
   - **Email:** `juan@email.com`
   - **Contraseña:** `user123`
   - **Rol:** USER

2. **Ana García**
   - **Email:** `ana@email.com`
   - **Contraseña:** `user123`
   - **Rol:** USER

3. **Carlos López**
   - **Email:** `carlos@email.com`
   - **Contraseña:** `user123`
   - **Rol:** USER

> 💡 **Nota:** Todos los usuarios se crean automáticamente al iniciar la aplicación. Las contraseñas están encriptadas con BCrypt.
