create table products (
   id       serial primary key,
   name     varchar(50),
   producer varchar(50),
   count    integer default 0,
   price    integer
)

create or replace function delete_product_if_count_zero_by_id(product_id integer)
returns void as $$
begin
    delete from products where id = product_id and count = 0;
end;
$$ language plpgsql;

create or replace procedure delete_product_if_count_zero_by_id_procedure(product_id integer)
language plpgsql
as $$
begin
    delete from products where id = product_id and count = 0;
end;
$$;
