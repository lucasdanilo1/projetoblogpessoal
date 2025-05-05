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
('Networking no Acelera Maker', 'Conheci muita gente boa e fiz contatos importantes durante o Acelera Maker.', DATEADD('DAY', -25, CURRENT_TIMESTAMP), 4, 5),
('Minha Experiência no Acelera Maker', 'Minha jornada no Acelera Maker foi fundamental para me tornar um desenvolvedor full-stack pronto para os desafios do mercado.', DATEADD('DAY', -4, CURRENT_TIMESTAMP), 2, 2),
('Spring Boot e Angular no Acelera Maker', 'Adorei a forma como o Acelera Maker ensina Spring Boot e Angular, tecnologias essenciais hoje em dia.', DATEADD('DAY', -6, CURRENT_TIMESTAMP), 2, 3),
('IA no Acelera Maker', 'A introdução à Inteligência Artificial no Acelera Maker abriu minha mente para novas possibilidades na área de tecnologia.', DATEADD('DAY', -1, CURRENT_TIMESTAMP), 3, 1),
('Projeto Final do Acelera Maker', 'O projeto final do Acelera Maker foi desafiador, mas muito gratificante! Construir uma aplicação completa com Spring e Angular solidificou meu aprendizado.', DATEADD('DAY', -5, CURRENT_TIMESTAMP), 1, 4);
