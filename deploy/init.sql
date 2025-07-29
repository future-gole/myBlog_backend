create database myblog;
drop database myBlog;
-- 1. Categories - 分类表 (已移除 slug)
CREATE TABLE Categories (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255) NOT NULL UNIQUE, -- 分类显示名称, e.g., "手作与生活"
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 2. Tags - 标签表 (已移除 slug)
CREATE TABLE Tags (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255) NOT NULL UNIQUE, -- 标签显示名称, e.g., "烘焙"
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 3. Posts - 文章表 (已移除 slug)
CREATE TABLE Posts (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       content TEXT, -- 文章正文
                       image_url VARCHAR(2048), -- 文章特色图片链接
                       published_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       category_id INT NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    -- 外键关联到 Categories 表
                       FOREIGN KEY (category_id) REFERENCES Categories(id) ON DELETE CASCADE,

    -- 确保在同一个分类下，文章的标题是唯一的
                       UNIQUE KEY uk_category_title (category_id, title)
);

-- 为文章 category_id 创建索引
CREATE INDEX idx_posts_category_id ON Posts(category_id);

-- 4. Post_Tags - 文章与标签的关联表
CREATE TABLE Post_Tags (
                           post_id INT NOT NULL,
                           tag_id INT NOT NULL,
                           PRIMARY KEY (post_id, tag_id),
                           FOREIGN KEY (post_id) REFERENCES Posts(id) ON DELETE CASCADE,
                           FOREIGN KEY (tag_id) REFERENCES Tags(id) ON DELETE CASCADE
);

-- 为两个外键都创建索引，以优化双向查询性能
CREATE INDEX idx_post_tags_post_id ON Post_Tags(post_id);
CREATE INDEX idx_post_tags_tag_id ON Post_Tags(tag_id);

-- 5. Links - 链接关系表
CREATE TABLE Links (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       source_post_id INT NOT NULL, -- 链接来源文章的 ID
                       target_post_id INT NOT NULL, -- 链接目标文章的 ID
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                       FOREIGN KEY (source_post_id) REFERENCES Posts(id) ON DELETE CASCADE,
                       FOREIGN KEY (target_post_id) REFERENCES Posts(id) ON DELETE CASCADE,
                       UNIQUE KEY uk_source_target (source_post_id, target_post_id)
);

-- 为两个外键都创建索引，对双向查询都至关重要
CREATE INDEX idx_links_source_id ON Links(source_post_id);
CREATE INDEX idx_links_target_id ON Links(target_post_id);

-- 6. Users - 用户表
CREATE TABLE Users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255) NOT NULL UNIQUE,
                       password_hash VARCHAR(255), -- 存储加密后的密码
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);