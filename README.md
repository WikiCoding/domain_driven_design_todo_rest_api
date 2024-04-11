# Auth Endpoints
- **/auth/register - Method POST** - RequestBody params are: username: String, password: String, role: String
- **/auth/login - Method POST** - RequestBody params are: username: String, password: String
- **/auth/refresh-token - Method POST**
- **/auth/logout - Method POST** - RequestBody params are: username: String, password: String

# Todo Endpoints
- **/todo - Method GET** - gets the user todos. With RequestHeader: Authorization Bearer token
- **/todo - Method POST** - RequestBody params are: todo: String, complete: boolean. With RequestHeader: Authorization Bearer token
- **/todo={id} - Method PUT** - RequestBody params are: todo: String, complete: boolean. With RequestHeader: Authorization Bearer token
- **/todo={id} - Method DELETE** - RequestHeader: Authorization Bearer token