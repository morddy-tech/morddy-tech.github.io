// Fetch project data from JSON file and render cards
async function loadProjects() {
    const container = document.getElementById('projects-container');
    if (!container) return;

    try {
        const response = await fetch('projects.json');
        if (!response.ok) throw new Error(`HTTP ${response.status}`);
        const projects = await response.json();

        if (!projects.length) {
            container.innerHTML = '<p class="error">No projects found.</p>';
            return;
        }

        container.innerHTML = '';
        projects.forEach(project => {
            const card = createProjectCard(project);
            container.appendChild(card);
        });

        observeCards(); // attach fade-in observer
    } catch (error) {
        console.error('Failed to load projects:', error);
        container.innerHTML = '<p class="error">Unable to load projects. Please try again later.</p>';
    }
}

// Create a safe DOM element for each project
function createProjectCard(project) {
    const card = document.createElement('div');
    card.className = 'project-card';

    let imageHtml = '';
    if (project.image) {
        imageHtml = `<img src="${escapeHtml(project.image)}" alt="${escapeHtml(project.title)}" class="project-image" loading="lazy">`;
    }

    card.innerHTML = `
        ${imageHtml}
        <div class="card-content">
            <span class="category">${escapeHtml(project.category)}</span>
            <h3>${escapeHtml(project.title)}</h3>
            <p>${escapeHtml(project.description)}</p>
            <div class="tech-stack">${escapeHtml(project.techStack)}</div>
            <a href="${escapeHtml(project.link)}" class="btn-outline" ${project.link.startsWith('http') ? 'target="_blank" rel="noopener noreferrer"' : ''}>${escapeHtml(project.linkText)}</a>
        </div>
    `;
    return card;
}

// Basic XSS protection
function escapeHtml(str) {
    if (!str) return '';
    return str.replace(/[&<>]/g, function(m) {
        if (m === '&') return '&amp;';
        if (m === '<') return '&lt;';
        if (m === '>') return '&gt;';
        return m;
    });
}

// Intersection Observer for fade-in cards
function observeCards() {
    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('show');
                observer.unobserve(entry.target);
            }
        });
    }, { threshold: 0.1 });

    document.querySelectorAll('.project-card').forEach(card => {
        observer.observe(card);
    });
}

// Start everything when DOM is ready
document.addEventListener('DOMContentLoaded', () => {
    loadProjects();
});