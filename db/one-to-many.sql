create table departments(
    id serial primary key,
    department_name varchar(255)
);

create table employees(
    id serial primary key,
    full_name varchar(100),
    department_id int references departments(id)
);

insert into departments(department_name) values
('Отдел продаж'),
('Бухгалтерия'),
('Отдел маркетинга'),
('IT-отдел'),
('Отдел кадров'),
('Юридический отдел'),   -- пустой
('Отдел логистики'),     -- пустой
('Отдел исследований');  -- пустой

-- Вставка данных в сотрудников
insert into employees(full_name, department_id) values
('Иванов Иван Иванович', 1),
('Петров Петр Петрович', 1),
('Сидорова Мария Алексеевна', 2),
('Кузнецова Анна Викторовна', 2),
('Смирнов Алексей Сергеевич', 3),
('Орлова Елена Дмитриевна', 3),
('Васильев Дмитрий Александрович', 4),
('Попова Наталья Игоревна', 4),
('Федоров Михаил Артемович', 5),
('Ковалёва Ольга Николаевна', 5);

-- 2. Выполнить запросы с left, right, full, cross соединениями
select * from employees
            left join departments on employees.department_id = departments.id;

select * from employees
            right join departments on employees.department_id = departments.id;

select * from employees
            full join departments on employees.department_id = departments.id;

select * from employees
            cross join departments;

-- 3. Используя left join найти департаменты, у которых нет работников

select
    departments.id,
    departments.department_name
from
    departments
left join employees on employees.department_id = departments.id
where employees.department_id is null;

-- 4. Используя left и right join написать запросы,
-- которые давали бы одинаковый результат
-- (порядок вывода колонок в эти запросах также должен быть идентичный).

select
    departments.id,
    departments.department_name,
    employees.id,
    employees.full_name
from
    departments
left join employees on employees.department_id = departments.id;

select
    departments.id,
    departments.department_name,
    employees.id,
    employees.full_name
from
    employees
right join departments on employees.department_id = departments.id;