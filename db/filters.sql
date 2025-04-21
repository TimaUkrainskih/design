-- 1.Написать запрос получение всех продуктов с типом "СЫР"
select
    p.product_name,
    tp.type_name
from
    products p
join
    type_product tp on tp.id = p.type_id
where
    tp.type_name = 'СЫР';

-- 2.Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
select * from products
where product_name like '%мороженое%';

-- 3.Написать запрос, который выводит все продукты, срок годности которых уже истек
select * from products
where expired_date < now();

-- 4.Написать запрос, который выводит самый дорогой продукт.
-- Запрос должен быть универсальный и находить все продукты с максимальной ценой.
-- Например, если в таблице есть продукт типа СЫР с ценой 200 и продукт типа МОЛОКО с ценой 200,
-- и эта цена максимальная во всей таблице, то запрос должен вывести оба этих продукта.

select
    p.product_name,
    p.price,
    t.type_name
from products p
join type_product t on p.type_id = t.id
where p.price = ( select max(price) from products);

-- 5. Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих.
-- В виде имя_типа, количество

select
    count(p.type_id) as "Количество",
    t.type_name as "Имя типа"
from products p
join type_product t on p.type_id = t.id
group by t.type_name;

-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select
    p.product_name,
    p.expired_date,
    p.price,
    t.type_name
from products p
join type_product t on p.type_id = t.id
where t.type_name in('СЫР', 'МОЛОКО');

-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
-- Под количеством подразумевается количество продуктов определенного типа.
-- Например, если есть тип "СЫР" и продукты "Сыр плавленный" и "Сыр моцарелла",
-- которые ему принадлежат, то количество продуктов типа "СЫР" будет 2.

select
    t.type_name as "Тип продукта",
    count(p.id) as "Количество"
from products p
join type_product t on p.type_id = t.id
group by t.type_name
having count(p.id) < 10;

-- 8. Вывести все продукты и их тип.
select
    p.product_name,
    p.expired_date,
    p.price,
    t.type_name
from products p
join type_product t on p.type_id = t.id;