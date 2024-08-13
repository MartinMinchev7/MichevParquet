INSERT INTO roles VALUES (1, 'CLIENT'), (2, 'ADMIN');

INSERT INTO users (`id`, `email`, `first_name`, `last_name`, `password`, `uuid`)
VALUES
    (1, 'maya@mail', 'Maya', 'Mincheva', '375ee7ae558be0ee49ad0671ec6329a1c7e2875374ed8780f6e6a6cceb4e55e1fbc678106a6fa09da8eb349b7cda41f3', '099e0c80-f1ea-471a-b0d3-13ae3990d788');

INSERT INTO users_roles (`user_id`, `role_id`)
VALUES
    (1, 1),
    (1, 2);
