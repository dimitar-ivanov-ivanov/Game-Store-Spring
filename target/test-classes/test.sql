INSERT INTO users(user_id,birth_date,email,first_name,gender,is_enabled,is_expired,is_locked,last_name,password,username)
VALUES
(1001,'1999-02-02','kiro.i.koriv@abv.bg','dimitar','MALE',1,0,0,'pekotv','123213adasbdfcde','petkoptko123da');

INSERT INTO users_roles(user_id,role_id) VALUES
(1001,1);
