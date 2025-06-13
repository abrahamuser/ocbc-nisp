create user mleb_core identified by mleb_core;
grant create session to mleb_core;
alter user MLEB_CORE DEFAULT TABLESPACE USERS quota unlimited on USERS;


create user mleb_mcb identified by mleb_mcb;
grant create session to mleb_mcb;
alter user mleb_mcb DEFAULT TABLESPACE USERS quota unlimited on USERS;


create user SESSION_OCBC identified by SESSION_OCBC;
grant create session to SESSION_OCBC;
alter user SESSION_OCBC DEFAULT TABLESPACE USERS quota unlimited on USERS;


create table SESSION_OCBC.wl_servlet_sessions
  			( wl_id VARCHAR2(100) NOT NULL,
    		wl_context_path VARCHAR2(100) NOT NULL,
    		wl_is_new CHAR(1),
    		wl_create_time NUMBER(20),
    		wl_is_valid CHAR(1),
    		wl_session_values LONG RAW,
    		wl_access_time NUMBER(20),
    		wl_max_inactive_interval INTEGER,
   			PRIMARY KEY (wl_id, wl_context_path) );