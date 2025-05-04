INSERT INTO usuario (nome, usuario, senha, foto) VALUES
('Jair Messias', 'admin@email.com', '$2a$10$ZYiP8Y4L6e/KP7.q1VfzAe0d9m8x6L7l.L.4g6j5K9h7k3N0f8G1S', null),
('Gerônimo Angulanus III', 'lucas@email.com', '$2a$10$ZYiP8Y4L6e/KP7.q1VfzAe0d9m8x6L7l.L.4g6j5K9h7k3N0f8G1S', null),
('Jallim Habbei', 'mariana@email.com', '$2a$10$ZYiP8Y4L6e/KP7.q1VfzAe0d9m8x6L7l.L.4g6j5K9h7k3N0f8G1S', null),
('Luis Guerreiro', 'luis.guerreiro@montreal.com.br', '$2a$10$ZYiP8Y4L6e/KP7.q1VfzAe0d9m8x6L7l.L.4g6j5K9h7k3N0f8G1S', null),
('Davi Brito', 'daniel.martins@email.com', '$2a$10$ZYiP8Y4L6e/KP7.q1VfzAe0d9m8x6L7l.L.4g6j5K9h7k3N0f8G1S', null);

INSERT INTO tema (descricao) VALUES
('Tecnologia'),
('IA'),
('Carreira'),
('Testes de Software');

INSERT INTO postagem (titulo, texto, data, tema_id, usuario_id) VALUES
('Networking no Acelera Maker', 'Conheci muita gente boa e fiz contatos importantes durante o Acelera Maker.', CURRENT_TIMESTAMP - INTERVAL '25 day', 4, 5),
('Minha Experiência no Acelera Maker', 'Minha jornada no Acelera Maker foi fundamental para me tornar um desenvolvedor full-stack pronto para os desafios do mercado.', CURRENT_TIMESTAMP - INTERVAL '4 day', 2, 2),
('Spring Boot e Angular no Acelera Maker', 'Adorei a forma como o Acelera Maker ensina Spring Boot e Angular, tecnologias essenciais hoje em dia.', CURRENT_TIMESTAMP - INTERVAL '6 day', 2, 3),
('IA no Acelera Maker', 'A introdução à Inteligência Artificial no Acelera Maker abriu minha mente para novas possibilidades na área de tecnologia.', CURRENT_TIMESTAMP - INTERVAL '1 day', 3, 1),
('Projeto Final do Acelera Maker', 'O projeto final do Acelera Maker foi desafiador, mas muito gratificante! Construir uma aplicação completa com Spring e Angular solidificou meu aprendizado.', CURRENT_TIMESTAMP - INTERVAL '5 day', 1, 4);
