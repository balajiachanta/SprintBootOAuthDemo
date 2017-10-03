delete from user;
insert into user (email, firstname, lastname, password, username) values ('abcdefgh@gmail.com','apple','iphone','�B�F�(&fD;bF�P�u�bi��"���!2y','abcdefgh@gmail.com');

delete from ENCODE_DETAILS;
INSERT INTO ENCODE_DETAILS(ITERATIONS,KEY_LENGTH,NAME,USER_NAME)VALUES(100,256,'salt','abcdefgh@gmail.com');

drop table if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);


INSERT INTO OAUTH_CLIENT_DETAILS(CLIENT_ID,RESOURCE_IDS,CLIENT_SECRET,SCOPE,AUTHORIZED_GRANT_TYPES,WEB_SERVER_REDIRECT_URI,AUTHORITIES,ACCESS_TOKEN_VALIDITY,REFRESH_TOKEN_VALIDITY,ADDITIONAL_INFORMATION,AUTOAPPROVE)VALUES
('registeredclient','oauth2-resource','clientsecret','read,write,trust','client_credentials,password,refresh_token','','',600,1800,'{}','read,write,trust');
drop table if exists oauth_client_token;
create table oauth_client_token (
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  token_id VARCHAR(255),
  token LONGVARBINARY,
);
drop table if exists oauth_access_token;
create table oauth_access_token (
  token_id VARCHAR(255),
  token LONGVARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication LONGVARBINARY,
  refresh_token VARCHAR(255)
);
 
drop table if exists oauth_refresh_token;
create table oauth_refresh_token (
  token_id VARCHAR(255),
  token LONGVARBINARY,
  authentication LONGVARBINARY 
);
 
drop table if exists oauth_code;
create table oauth_code (
  code VARCHAR(255), authentication LONGVARBINARY
);
 
drop table if exists oauth_approvals;
create table oauth_approvals (
    userId VARCHAR(255),
    clientId VARCHAR(255),
    scope VARCHAR(255),
    status VARCHAR(10),
    expiresAt TIMESTAMP,
    lastModifiedAt TIMESTAMP
);
 
drop table if exists ClientDetails;
create table ClientDetails (
  appId VARCHAR(255) PRIMARY KEY,
  resourceIds VARCHAR(255),
  appSecret VARCHAR(255),
  scope VARCHAR(255),
  grantTypes VARCHAR(255),
  redirectUrl VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(255)
);
